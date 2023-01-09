from django.urls import path
from .views import obras_view

urlpatterns = [
    path('obras/', obras_view.ObrasCriarListar.as_view()),
    path('obras/<int:id>', obras_view.ObrasEditarDeletar.as_view()),
]