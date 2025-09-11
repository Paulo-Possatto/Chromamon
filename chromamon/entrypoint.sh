#!/bin/sh

ENVIRONMENT=${SPRING_PROFILES_ACTIVE:-local}
KEYSTORE_PASSWORD=${KEYSTORE_PASSWORD:-changeit}
COMPANY=${COMPANY:-MyCompany}
COUNTRY=${COUNTRY:-ES}

if [ -f /.dockerenv ] || ( [ -f /proc/1/cgroup ] && grep -q docker /proc/1/cgroup ); then
    IS_DOCKER=true
    echo "Docker environment detected"
else
    IS_DOCKER=false
    echo "Local Java execution detected"
fi

case "$ENVIRONMENT" in
    "local")
        if [ "$IS_DOCKER" = "true" ]; then
            KEYSTORE_PATH="/app/ssl/keystore.p12"
        else
            KEYSTORE_PATH="src/main/resources/ssl/keystore.p12"
        fi
        SAN_EXTENSIONS="DNS:localhost,IP:127.0.0.1"
        SSL_AUTO_GENERATE=true
        ORGANIZATION_UNIT="Local"
        echo "Local environment (localhost/127.0.0.1)"
        ;;
    "dev")
        KEYSTORE_PATH="/app/ssl/keystore.p12"
        SAN_EXTENSIONS="IP:192.168.1.40"
        SSL_AUTO_GENERATE=true
        ORGANIZATION_UNIT="Development"
        echo "Development environment (192.168.1.40)"
        ;;
    "qa")
        KEYSTORE_PATH="/app/ssl/qa-keystore.p12"
        SAN_EXTENSIONS="DNS:qa-app.example.com"
        SSL_AUTO_GENERATE=true
        ORGANIZATION_UNIT="Quality"
        echo "QA environment"
        ;;
    "prod")
        KEYSTORE_PATH="/app/ssl/prod-keystore.p12"
        SAN_EXTENSIONS="DNS:app.example.com,DNS:www.app.example.com"
        SSL_AUTO_GENERATE=true
        ORGANIZATION_UNIT="Production"
        echo "Production environment"
        ;;
    *)
        if [ "$IS_DOCKER" = "true" ]; then
            KEYSTORE_PATH="/app/ssl/keystore.p12"
        else
            KEYSTORE_PATH="src/main/resources/ssl/keystore.p12"
        fi
        SAN_EXTENSIONS="DNS:localhost,IP:127.0.0.1"
        SSL_AUTO_GENERATE=true
        ORGANIZATION_UNIT="Local"
        echo "Default environment (localhost)"
        ;;
esac

echo "Keystore path: $KEYSTORE_PATH"
echo "SAN Extensions: $SAN_EXTENSIONS"

if [ "$SSL_AUTO_GENERATE" = "true" ]; then
    if [ ! -f "$KEYSTORE_PATH" ]; then
        echo "Generating SSL certificate for $ENVIRONMENT..."

        mkdir -p "$(dirname "$KEYSTORE_PATH")"

        keytool -genkeypair -alias chroma-"$ENVIRONMENT" -keyalg RSA -keysize 2048 -storetype PKCS12 \
            -keystore "$KEYSTORE_PATH" -validity 3650 \
            -storepass "$KEYSTORE_PASSWORD" -keypass "$KEYSTORE_PASSWORD" \
            -dname "CN=$ENVIRONMENT-app, OU=$ORGANIZATION_UNIT, O=$COMPANY, C=$COUNTRY" \
            -ext "SAN=$SAN_EXTENSIONS"

        if [ $? -eq 0 ]; then
            echo "SSL Certificate generated successfully: $KEYSTORE_PATH"
        else
            echo "Failed to generate SSL certificate"
            exit 1
        fi
    else
        echo "SSL Certificate already exists: $KEYSTORE_PATH"
    fi
else
    echo "SSL auto-generation disabled for $ENVIRONMENT"
    if [ ! -f "$KEYSTORE_PATH" ]; then
        echo "ERROR: SSL Certificate not found for $ENVIRONMENT"
        echo "Provide a valid certificate at: $KEYSTORE_PATH"
        exit 1
    fi
    echo "SSL Certificate validated: $KEYSTORE_PATH"
fi

if [ "$IS_DOCKER" = "true" ]; then
    export SERVER_SSL_KEY_STORE="file:$KEYSTORE_PATH"
    export SERVER_SSL_KEY_STORE_PASSWORD="$KEYSTORE_PASSWORD"
    export SERVER_PORT=8443

    echo "Starting application in $ENVIRONMENT environment"
    echo "HTTPS available at port 8443"
    exec java -jar app.jar
else
    echo "Local execution detected"
    echo "SSL certificate ready for: $KEYSTORE_PATH"
    echo "Run with: mvn spring-boot:run -Dspring-boot.run.profiles=$ENVIRONMENT"
    exit 0
fi