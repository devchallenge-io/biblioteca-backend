from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from ..serializers.obras_serializer import ObrasSerializer
from ..models import Obras
from django.shortcuts import get_object_or_404

class ObrasCriarListar(APIView):
    def post(self, request):
        serializer = ObrasSerializer(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status = status.HTTP_201_CREATED)
        return Response(serializer.errors, status = status.HTTP_400_BAD_REQUEST)
    
    def get(self, request):
        obras = Obras.objects.all()
        serializer = ObrasSerializer(obras, many = True)
        return Response(serializer.data, status = status.HTTP_200_OK)

class ObrasEditarDeletar(APIView):
    def delete(self, request, id):
        obra = get_object_or_404(Obras, id=id)
        obra.delete()
        return Response(status = status.HTTP_204_NO_CONTENT)

    def put(self, request, id):
        obra = get_object_or_404(Obras, id=id)
        serializer = ObrasSerializer(obra, data = request.data)
        if serializer.is_valid():
            serializer.save(force_updata=True)
            return Response(serializer.data, status = status.HTTP_200_OK)
        return Response(serializer.errors, status = status.HTTP_400_BAD_REQUEST)