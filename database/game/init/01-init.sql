-- Game Service Database Initialization
-- This script runs when the PostgreSQL container starts for the first time

-- Create extensions if needed
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create additional schemas if needed
-- CREATE SCHEMA IF NOT EXISTS heroes;
-- CREATE SCHEMA IF NOT EXISTS battles;
-- CREATE SCHEMA IF NOT EXISTS guilds;

-- Grant permissions
GRANT ALL PRIVILEGES ON DATABASE game_db TO postgres;

-- Create indexes for better performance (will be created by JPA, but can add custom ones here)
-- CREATE INDEX IF NOT EXISTS idx_player_id ON players(user_id);
-- CREATE INDEX IF NOT EXISTS idx_hero_player_id ON heroes(player_id);
-- CREATE INDEX IF NOT EXISTS idx_battle_player_id ON battles(player_id);

-- Insert any initial data if needed
-- INSERT INTO hero_templates (name, rarity, base_attack, base_defense) VALUES 
--   ('Warrior', 'COMMON', 100, 80) ON CONFLICT DO NOTHING,
--   ('Mage', 'COMMON', 120, 60) ON CONFLICT DO NOTHING,
--   ('Archer', 'COMMON', 110, 70) ON CONFLICT DO NOTHING;


