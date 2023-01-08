from django.urls import path
from .views import obras_view
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('obras/', obras_view.ObrasCriarListar.as_view()),
    path('obras/<int:id>', obras_view.ObrasEditarDeletar.as_view()),
]+ static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)