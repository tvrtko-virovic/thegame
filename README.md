# TheGame MMO - Microservices Architecture

An Idle Heroes-style MMO game built with Spring Boot microservices.

## Architecture

- **Account Service** (Port 8081): User management, authentication, social features
- **Game Service** (Port 8082): Game logic, heroes, battles, progression
- **Account Database** (Port 5432): PostgreSQL for account data
- **Game Database** (Port 5433): PostgreSQL for game data
- **Redis** (Port 6379): Caching and session storage

## Quick Start

### Prerequisites

- Java 21+
- Maven 3.6+
- Docker Desktop
- PostgreSQL (via Docker)

### 1. Start Databases

```powershell
# Start PostgreSQL databases with Docker
.\start-databases.ps1
```

### 2. Run Services

```bash
# Terminal 1 - Account Service
cd account-service
mvn spring-boot:run

# Terminal 2 - Game Service
cd game-service
mvn spring-boot:run
```

### 3. Verify Services

- Account Service: http://localhost:8081/api/health
- Game Service: http://localhost:8082/api/health

## Database Configuration

### Account Database

- **Host**: localhost:5432
- **Database**: account_db
- **Username**: postgres
- **Password**: password

### Game Database

- **Host**: localhost:5433
- **Database**: game_db
- **Username**: postgres
- **Password**: password

## Development

### Project Structure

```
thegame/
├── account-service/          # Account & Authentication Service
│   ├── src/main/java/
│   └── src/main/resources/
├── game-service/             # Game Server & World Service
│   ├── src/main/java/
│   └── src/main/resources/
├── database/                 # Database initialization scripts
│   ├── account/init/
│   └── game/init/
├── docker-compose.yml        # Database containers
└── README.md
```

### Useful Commands

```powershell
# Start databases
.\start-databases.ps1

# Stop databases
.\stop-databases.ps1

# View database logs
docker-compose logs -f account-db
docker-compose logs -f game-db

# Reset databases (removes all data)
docker-compose down -v
.\start-databases.ps1
```

## Game Features (Planned)

### MVP Features

- Hero collection (gacha system)
- Team formation (5 heroes)
- Auto-battle combat
- Campaign progression
- Random arena PvP
- Basic guild system

### Future Features

- Guild wars
- Tournament system
- Premium currency (QoL only)
- Advanced progression systems
- Social features (chat, friends)

## Technology Stack

- **Backend**: Spring Boot 3.2.0, Java 21
- **Database**: PostgreSQL 15
- **Caching**: Redis 7
- **Containerization**: Docker
- **Build Tool**: Maven
- **Architecture**: Microservices
