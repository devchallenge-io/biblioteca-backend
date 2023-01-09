from ..models import Obras
from django.shortcuts import get_object_or_404

def listar_obras():
    return Obras.objects.all()

def listar_obra_id(id):
    return get_object_or_404(Obras, id=id)

def criar_obra(obra):
    Obras.objects.create(
        titulo = obra.titulo,
        editora = obra.editora,
        foto = obra.foto,
        autores = obra.autores
    )

def editar_obra(obra_antiga, obra_nova):
    obra_antiga.titulo = obra_nova.titulo
    obra_antiga.editora = obra_nova.editora
    obra_antiga.foto = obra_nova.foto
    obra_antiga.autores = obra_nova.autores
    obra_antiga.save(force_update = True)

def remover_obra(obra):
    obra.delete()