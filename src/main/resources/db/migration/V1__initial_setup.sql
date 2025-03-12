CREATE TYPE priority AS ENUM ('LOW', 'MEDIUM', 'HIGH');
CREATE TYPE status AS ENUM ('OPEN', 'IN_PROGRESS', 'COMPLETED');
CREATE CAST ( varchar AS priority ) WITH INOUT AS IMPLICIT;
CREATE CAST ( varchar AS status ) WITH INOUT AS IMPLICIT;

CREATE TABLE task
(
    id             BIGSERIAL PRIMARY KEY,
    title          VARCHAR(255)  NOT NULL,
    description    VARCHAR(2000) NOT NULL,
    notes          TEXT,
    priority       priority      NOT NULL,
    status         status        NOT NULL,
    created_date   TIMESTAMP,
    due_date       TIMESTAMP,
    completed_date TIMESTAMP
);