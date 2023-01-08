from rest_framework import serializers
from ..models import Obras

class ObrasSerializer(serializers.ModelSerializer):
    class Meta:
        model = Obras
        fields = ('id','titulo', 'editora', 'foto', 'autores')