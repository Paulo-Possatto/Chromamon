#!/bin/sh

ENVIRONMENT=${SPRING_PROFILES_ACTIVE:-local}
KEYSTORE_PASSWORD=${KEYSTORE_PASSWORD:-changeit}
COMPANY=${COMPANY:-MyCompany}
COUNTRY=${COUNTRY:-ES}
ORGANIZATION_UNIT=""

case "$ENVIRONMENT" in
    "local")
        KEYSTORE_PATH="src/main/resources/ssl/keystore.p12"
        SAN_EXTENSIONS="DNS:localhost,IP:127.0.0.1"
        SSL_AUTO_GENERATE=true
        ORGANIZATION_UNIT="Local"
        echo "Local environment"
        ;;
    "dev")
        KEYSTORE_PATH="/app/ssl/keystore.p12"
        SAN_EXTENSIONS="IP:192.168.1.40"
        SSL_AUTO_GENERATE=true
        ORGANIZATION_UNIT="Development"
        echo "Development environment"
        ;;
    "qa")
        KEYSTORE_PATH="/app/ssl/qa-keystore.p12"
        SAN_EXTENSIONS="DNS:qa-app.example.com"
        SSL_AUTO_GENERATE=false
        ORGANIZATION_UNIT="Quality"
        echo "QA environment"
        ;;
    "prod")
        KEYSTORE_PATH="/app/ssl/prod-keystore.p12"
        SAN_EXTENSIONS="DNS:app.example.com,DNS:www.app.example.com"
        SSL_AUTO_GENERATE=false
        ORGANIZATION_UNIT="Production"
        echo "Production environment"
        ;;
    *)
        KEYSTORE_PATH="/app/ssl/keystore.p12"
        SAN_EXTENSIONS="DNS:localhost,IP:127.0.0.1"
        SSL_AUTO_GENERATE=true
        ORGANIZATION_UNIT="Local"
        echo "Default environment (localhost)"
        ;;
esac

echo "Keystore path: $KEYSTORE_PATH"
echo "SAN Extensions: $SAN_EXTENSIONS"

if [ "$SSL_AUTO_GENERATE" = "true" ] && [ ! -f "$KEYSTORE_PATH" ]; then
    echo "Generating SSL certificate for $ENVIRONMENT..."

    mkdir -p "$(dirname $KEYSTORE_PATH)"

    keytool -genkeypair -alias chroma"$ENVIRONMENT" -keyalg RSA -keysize 2048 -storetype PKCS12 \
        -keystore "$KEYSTORE_PATH" -validity 3650 \
        -storepass "$KEYSTORE_PASSWORD" -keypass "$KEYSTORE_PASSWORD" \
        -dname "CN=$ENVIRONMENT-app, OU=$ORGANIZATION_UNIT, O=$COMPANY, C=$COUNTRY" \
        -ext "SAN=$SAN_EXTENSIONS"

    echo "SSL Certificate generated: $KEYSTORE_PATH"
elif [ "$SSL_AUTO_GENERATE" = "false" ] && [ ! -f "$KEYSTORE_PATH" ]; then
    echo "ERROR: SSL Certificate not found for $ENVIRONMENT"
    echo "For QA/PROD environments, provide a certificate in: $KEYSTORE_PATH"
    exit 1
fi

export SERVER_SSL_KEY_STORE="file:$KEYSTORE_PATH"
export SERVER_SSL_KEY_STORE_PASSWORD="$KEYSTORE_PASSWORD"
export SERVER_PORT=8443

echo "Starting application at the $ENVIRONMENT environment"
echo "HTTPS available at port 8443"
if [ "$ENVIRONMENT" != "local" ]; then
    exec java -jar app.jar
fi
