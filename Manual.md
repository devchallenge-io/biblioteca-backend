# Instalação

1. tenha o python 3.11.1 instalado

2. Clone o repositório

3. Intale as dependências que estão no arquivo requirements.txt utilizando o comando ```pip install -r requirements.txt```

# Modo de uso

Após a instalação concluida, ative o ambiente virtual ```venv``` com o seguinte comando
```venv\scripts\activate```

Em seguida ative o servidor com o seguinte comando ```python manage.py runserver```


# Rotas
| método | rota |
| --- | --- |
| POST | ```http://127.0.0.1:8000/api/obras/``` |
| GET | ```http://127.0.0.1:8000/api/obras/``` |
| PUT | ```http://127.0.0.1:8000/api/obras/ID``` |
| DELETE | ```http://127.0.0.1:8000/api/obras/ID```|