INSERT INTO public.role(
            id, nome, descricao)
    VALUES (1, 'ROLE_ADMIN','Administrador'),(2, 'ROLE_ADVOGADO','Advogado');

INSERT INTO public.usuario(
            id, login, senha, nome)
    VALUES (1, 'admin', '$2a$10$u1aRNtUcCm6CXi2hjEPJoepvFWT/puJX92Z3dzO0KAMNr7l4gh022', 'Admin');