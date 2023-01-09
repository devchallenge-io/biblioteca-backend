class Obras:
    def __init__(self, titulo, editora, foto, autores):
        self.__titulo = titulo
        self.__editora = editora
        self.__foto = foto
        self.__autores = autores
    
    @property
    def titulo(self):
        return self.__titulo
    
    @titulo.setter
    def titulo(self, titulo):
        self.__titulo = titulo
    
    @property
    def editora(self):
        return self.__editora
    
    @editora.setter
    def editora(self, editora):
        self.__editora = editora
    
    @property
    def foto(self):
        return self.__foto
    
    @foto.setter
    def foto(self, foto):
        self.__foto = foto

    @property
    def autores(self):
        return self.__autores
    
    @autores.setter
    def autores(self, autores):
        self.__autores = autores