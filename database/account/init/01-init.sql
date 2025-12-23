-- Account Service Database Initialization
-- This script runs when the PostgreSQL container starts for the first time

-- Create extensions if needed
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create additional schemas if needed
-- CREATE SCHEMA IF NOT EXISTS auth;
-- CREATE SCHEMA IF NOT EXISTS social;

-- Grant permissions
GRANT ALL PRIVILEGES ON DATABASE account_db TO postgres;

-- Create indexes for better performance (will be created by JPA, but can add custom ones here)
-- CREATE INDEX IF NOT EXISTS idx_user_email ON users(email);
-- CREATE INDEX IF NOT EXISTS idx_user_username ON users(username);

-- Insert any initial data if needed
-- INSERT INTO roles (name, description) VALUES ('USER', 'Regular user') ON CONFLICT DO NOTHING;
-- INSERT INTO roles (name, description) VALUES ('ADMIN', 'Administrator') ON CONFLICT DO NOTHING;


