-- V9__Create_database_functions.sql
-- Migration para criar funções úteis do banco
-- Função para atualizar updated_at automaticamente
CREATE
OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at
= CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$
language 'plpgsql';

-- Triggers para atualizar automaticamente updated_at
CREATE TRIGGER update_users_updated_at
    BEFORE UPDATE
    ON users.users_data
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_user_permissions_updated_at
    BEFORE UPDATE
    ON users.user_permissions
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_transformers_updated_at
    BEFORE UPDATE
    ON transformers.transformers
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_analyses_updated_at
    BEFORE UPDATE
    ON analyses.analyses
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_diagnostics_updated_at
    BEFORE UPDATE
    ON diagnostic.diagnostics
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();