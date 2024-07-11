-- V2__criar_tabela_pessoa.sql

CREATE TABLE pessoa (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    logradouro VARCHAR(150),
    numero VARCHAR(10),
    complemento VARCHAR(150),
    bairro VARCHAR(50),
    cep VARCHAR(20),
    cidade VARCHAR(50),
    estado VARCHAR(50),
    ativo BOOLEAN NOT NULL
);

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES
('João Silva', 'Rua das Flores', '123', 'Apto 1', 'Centro', '12345-678', 'São Paulo', 'SP', true),
('Maria Oliveira', 'Avenida Brasil', '456', '', 'Jardim América', '23456-789', 'Rio de Janeiro', 'RJ', true),
('Carlos Pereira', 'Rua XV de Novembro', '789', 'Bloco B', 'Bela Vista', '34567-890', 'Curitiba', 'PR', true),
('Ana Costa', 'Rua das Palmeiras', '101', '', 'Copacabana', '45678-901', 'Rio de Janeiro', 'RJ', true),
('José Souza', 'Avenida das Nações', '202', 'Apto 12', 'Centro', '56789-012', 'Brasília', 'DF', true),
('Paula Lima', 'Rua São João', '303', 'Casa 5', 'Vila Mariana', '67890-123', 'São Paulo', 'SP', true),
('Pedro Santos', 'Avenida Paulista', '404', 'Sala 10', 'Bela Vista', '78901-234', 'São Paulo', 'SP', true),
('Juliana Mendes', 'Rua Augusta', '505', '', 'Jardins', '89012-345', 'São Paulo', 'SP', true),
('Ricardo Alves', 'Rua Sete de Setembro', '606', 'Apto 7', 'Centro', '90123-456', 'Porto Alegre', 'RS', true),
('Fernanda Almeida', 'Avenida Ipiranga', '707', 'Casa 3', 'Vila Mariana', '01234-567', 'São Paulo', 'SP', true);
