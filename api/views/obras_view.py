from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from ..serializers.obras_serializer import ObrasSerializer, ObrasSerializerCadastro
from ..services import obras_service
from ..entidades.obras import Obras
from ..pagination import PaginationCustomizada


class ObrasCriarListar(APIView):
    def post(self, request):
        serializer = ObrasSerializerCadastro(data = request.data)
        if serializer.is_valid():
            titulo = serializer.validated_data['titulo']
            editora = serializer.validated_data['editora']
            foto = serializer.validated_data['foto']
            autores = serializer.validated_data['autores']
            nova_obra = Obras(
                titulo = titulo,
                editora = editora,
                foto = foto,
                autores = autores
            )
            obras_service.criar_obra(nova_obra)
            return Response(serializer.data, status = status.HTTP_201_CREATED)
        return Response(serializer.errors, status = status.HTTP_400_BAD_REQUEST)
    
    def get(self, request):
        paginacao = PaginationCustomizada()
        obras = obras_service.listar_obras()
        resultado = paginacao.paginate_queryset(obras, request)
        serializer = ObrasSerializer(resultado, context = {'request': request}, many = True)
        return paginacao.get_paginated_response(serializer.data)

class ObrasEditarDeletar(APIView):
    def delete(self, request, id):
        obra = obras_service.listar_obra_id(id)
        if obra:
            obras_service.remover_obra(obra)
            return Response(status = status.HTTP_204_NO_CONTENT)

    def put(self, request, id):
        obra = obras_service.listar_obra_id(id)
        serializer = ObrasSerializer(obra, context = {'request': request}, data = request.data)
        if serializer.is_valid():
            titulo = serializer.validated_data['titulo']
            editora = serializer.validated_data['editora']
            foto = serializer.validated_data['foto']
            autores = serializer.validated_data['autores']
            nova_obra = Obras(
                titulo = titulo,
                editora = editora,
                foto = foto,
                autores = autores
            )
            obras_service.editar_obra(obra, nova_obra)
            return Response(serializer.data, status = status.HTTP_200_OK)
        return Response(serializer.errors, status = status.HTTP_400_BAD_REQUEST)