-- Criar tipos ENUM personalizados
CREATE TYPE tipo_usuario_enum AS ENUM ('ADM', 'ALUNO', 'PROFESSOR');

-- Tabela Usuario
CREATE TABLE Usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario tipo_usuario_enum NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);

-- Tabela Materia
CREATE TABLE Materia (
    id_materia SERIAL PRIMARY KEY,
    nome_materia VARCHAR(255) NOT NULL UNIQUE
);

-- Tabela Turma
CREATE TABLE Turma (
    id_turma SERIAL PRIMARY KEY,
    nome_turma VARCHAR(255) NOT NULL UNIQUE,
    id_materia INT REFERENCES Materia(id_materia),
    id_professor INT REFERENCES Usuario(id_usuario)
);

-- Tabela de Relacionamento Turma_Aluno
CREATE TABLE Turma_Aluno (
    id_turma_aluno SERIAL PRIMARY KEY,
    id_turma INT REFERENCES Turma(id_turma),
    id_aluno INT REFERENCES Usuario(id_usuario)
);

-- Tabela Eventos
CREATE TABLE Eventos (
    id_evento SERIAL PRIMARY KEY,
    titulo_evento VARCHAR(255) NOT NULL,
    data_evento TIMESTAMP NOT NULL,
    id_turma INT REFERENCES Turma(id_turma),
    descricao TEXT
);

-- Tabela Presenca
CREATE TABLE Presenca (
    id_presenca SERIAL PRIMARY KEY,
    id_aluno INT REFERENCES Usuario(id_usuario), 	
    id_turma INT REFERENCES Turma(id_turma),
    data_presenca TIMESTAMP NOT NULL
);

-- Tabela Nota
CREATE TABLE Nota (
    id_nota SERIAL PRIMARY KEY,
    id_aluno INT REFERENCES Usuario(id_usuario),
    id_evento INT REFERENCES Eventos(id_evento),
    nota DECIMAL(5,2) NOT NULL,
    data_lancamento TIMESTAMP NOT NULL
);


-- Inserts na tabela Usuario
INSERT INTO Usuario (nome, email, senha, tipo_usuario, data_criacao) VALUES 
('Ana Silva', 'ana.silva@email.com', 'senha123hashed', 'ADM', NOW()),
('Carlos Souza', 'carlos.souza@email.com', 'senha456hashed', 'ALUNO', NOW()),
('Maria Santos', 'maria.santos@email.com', 'senha789hashed', 'PROFESSOR', NOW()),
('José Oliveira', 'jose.oliveira@email.com', 'senha012hashed', 'ALUNO', NOW());

-- Inserts na tabela Materia
INSERT INTO Materia (nome_materia) VALUES 
('Matemática'),
('História'),
('Física'),
('Química');

-- Inserts na tabela Turma
INSERT INTO Turma (nome_turma, id_materia, id_professor) VALUES 
('Turma A - Matemática', 1, 3),
('Turma B - História', 2, 3),
('Turma C - Física', 3, 3),
('Turma D - Química', 4, 3);

-- Inserts na tabela Turma_Aluno
INSERT INTO Turma_Aluno (id_turma, id_aluno) VALUES 
(1, 2),
(1, 4),
(2, 2),
(3, 4);

-- Inserts na tabela Eventos
INSERT INTO Eventos (titulo_evento, data_evento, id_turma, descricao) VALUES 
('Prova 1 de Matemática', '2024-08-10 08:00:00', 1, 'Primeira prova do semestre de Matemática.'),
('Trabalho de História', '2024-08-15 10:00:00', 2, 'Trabalho sobre a Revolução Francesa.'),
('Prova 1 de Física', '2024-08-20 09:00:00', 3, 'Primeira prova do semestre de Física.'),
('Prova 1 de Química', '2024-08-25 11:00:00', 4, 'Primeira prova do semestre de Química.');

-- Inserts na tabela Presenca
INSERT INTO Presenca (id_aluno, id_turma, data_presenca) VALUES 
(2, 1, '2024-08-01 08:00:00'),
(4, 1, '2024-08-01 08:00:00'),
(2, 2, '2024-08-01 10:00:00'),
(4, 3, '2024-08-01 09:00:00');

-- Inserts na tabela Nota
INSERT INTO Nota (id_aluno, id_evento, nota, data_lancamento) VALUES 
(2, 1, 8.5, '2024-08-10 15:00:00'),
(4, 1, 7.0, '2024-08-10 15:30:00'),
(2, 2, 9.0, '2024-08-15 12:00:00'),
(4, 3, 8.0, '2024-08-20 13:00:00');

-- 1. Consultar todos os usuários com seu tipo de usuário
SELECT nome, email, tipo_usuario 
FROM Usuario;

-- 2. Consultar todas as turmas e os nomes dos professores responsáveis
SELECT t.nome_turma, m.nome_materia, u.nome AS nome_professor
FROM Turma t
JOIN Materia m ON t.id_materia = m.id_materia
JOIN Usuario u ON t.id_professor = u.id_usuario;

-- 3. Consultar todos os alunos matriculados em uma turma específica (por exemplo, Turma A - Matemática)
SELECT u.nome AS nome_aluno, t.nome_turma
FROM Turma_Aluno ta
JOIN Usuario u ON ta.id_aluno = u.id_usuario
JOIN Turma t ON ta.id_turma = t.id_turma
WHERE t.nome_turma = 'Turma A - Matemática';

-- 4. Consultar todas as presenças registradas de um aluno específico (por exemplo, Carlos Souza)
SELECT u.nome AS nome_aluno, t.nome_turma, p.data_presenca, p.metodo_validacao
FROM Presenca p
JOIN Usuario u ON p.id_aluno = u.id_usuario
JOIN Turma t ON p.id_turma = t.id_turma
WHERE u.nome = 'Carlos Souza';

-- 5. Consultar todas as notas de um aluno específico (por exemplo, José Oliveira)
SELECT u.nome AS nome_aluno, e.titulo_evento, n.nota, n.data_lancamento
FROM Nota n
JOIN Usuario u ON n.id_aluno = u.id_usuario
JOIN Eventos e ON n.id_evento = e.id_evento
WHERE u.nome = 'José Oliveira';

-- 6. Consultar todas as turmas em que um professor específico ensina (por exemplo, Maria Santos)
SELECT t.nome_turma, m.nome_materia
FROM Turma t
JOIN Materia m ON t.id_materia = m.id_materia
JOIN Usuario u ON t.id_professor = u.id_usuario
WHERE u.nome = 'Maria Santos';

-- 7. Consultar todos os eventos programados para uma turma específica (por exemplo, Turma A - Matemática)
SELECT e.titulo_evento, e.data_evento, e.descricao
FROM Eventos e
JOIN Turma t ON e.id_turma = t.id_turma
WHERE t.nome_turma = 'Turma A - Matemática';

-- 8. Consultar a média de notas de um aluno específico em todos os eventos
SELECT u.nome AS nome_aluno, AVG(n.nota) AS media_notas
FROM Nota n
JOIN Usuario u ON n.id_aluno = u.id_usuario
WHERE u.nome = 'Carlos Souza'
GROUP BY u.nome;

-- 9. Consultar a lista de todas as matérias oferecidas na instituição
SELECT nome_materia 
FROM Materia;

-- 10. Consultar a lista de todos os alunos matriculados em mais de uma turma
SELECT u.nome AS nome_aluno, COUNT(ta.id_turma) AS numero_turmas
FROM Turma_Aluno ta
JOIN Usuario u ON ta.id_aluno = u.id_usuario
GROUP BY u.nome
HAVING COUNT(ta.id_turma) > 1;
