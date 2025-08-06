# Analysis Service

## What does it do?

## Structure

```text
src/main/java/es/paulopossatto/chromamon/analysisservice
├── application/                                    
│   ├── dto/
│   │   ├── request/
│   │   └── response/
│   ├── exception/
│   │   ├── ApplicationExceptionHandler.java (Class)     
│   │   ├── BadRequestException.java (Exception)
│   │   ├── CustomException.java (Exception)
│   │   ├── ForbiddenException.java (Exception)
│   │   ├── InternalServerError.java (Exception)
│   │   ├── NotAcceptableException.java (Exception)
│   │   ├── NotFoundException.java (Exception)
│   │   └── Unauthorized.java (Exception)
│   ├── ports/                                      
│   │   ├── input/                                 
│   │   └── output/  
│   │       ├── AnalysisRepositoryPort.java (Interface)
│   │       ├── ChromatographyRepositoryPort.java (Interface)
│   │       └── ObservationRepositoryPort.java (Interface)
│   └── service/                                    
│
├── domain/
│   ├── enums/
│   │   ├── ExtractionMethod.java (Enum)
│   │   └── OilType.java (Enum)
│   ├── model/
│   │   ├── Analysis.java (Record)
│   │   ├── Chromatography.java (Record)
│   │   └── Observation.java (Record)
│   └── service/
│
├── infrastructure/                                 
│   ├── config/
│   │   ├── EnvConfig.java (Class)
│   │   └── OpenApiConfig.java (Class)
│   ├── converters/
│   │   ├── ExtractionMethod.java (Class)
│   │   └── OilTypeConverter.java (Class)
│   ├── entity/
│   │   ├── AnalysisEntity.java (Record)
│   │   ├── ChromatographyEntity.java (Record)
│   │   └── ObservationEntity.java (Record)
│   ├── input/
│   │   ├── kafka/
│   │   └── rest/
│   │   │   ├── annotations/
│   │   │   └── controllers/
│   ├── mapper/
│   │   ├── ChromatographyEntityMapper.java (Class)
│   │   └── ObservationEntityMapper.java (Class)
│   ├── output/ --
│   │   ├── kafka/
│   │   ├── client/
│   │   └── persistence/
│   │       ├── adapters/
│   │       │   ├── ChromatographyRepositoryAdapter.java (class)
│   │       │   └── ObservationRepositoryAdapter.java (Class)
│   │       └── repositories/
│   │           ├── AnalysisJpaRepository.java (Interface)
│   │           ├── ChromatographyJpaRepository.java (Interface)
│   │           └── ObservationJpaRepository.java (Interface)
│   └── security/ --
│       ├── JwtAuthFilter.java (Class)
│       ├── JwtTokenUtil.java (Class)
│       ├── ProfileChecker.java (Class)
│       ├── RolesEnum.java (Enum)
│       └── SecurityConfig.java (Class)
│
└── ChromamonAnalysisServiceApplication.java  (Class)
```

## API Documentation:

When the application starts, you can check
the [Swagger](http://localhost:8080/swagger-ui/index.html) file at
``http://localhost:8080/swagger-ui/index.html``, as long as
you keep the application port as 8080. If not, change the URL
to the port you're using

## Testing methods:

### Acceptance:
