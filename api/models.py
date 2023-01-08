from django.db import models

class Obras(models.Model):
    titulo = models.CharField(max_length = 50, blank = False, null = False)
    editora = models.CharField(max_length = 50, blank = False, null = False)
    foto = models.URLField(null = True)
    autores = models.CharField(max_length = 100, blank = False, null = False)