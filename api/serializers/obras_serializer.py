from rest_framework import serializers
from ..models import Obras
from rest_framework.reverse import reverse

class ObrasSerializer(serializers.ModelSerializer):
    links = serializers.SerializerMethodField()
    class Meta:
        model = Obras
        fields = ('id','titulo', 'editora', 'foto', 'autores', 'links')

    def get_links(self, obj):
        request = self.context['request']
        return {
            'delete': reverse('obras-detalhes', kwargs = {'id': obj.pk}, request = request),
            'put': reverse('obras-detalhes', kwargs = {'id': obj.pk}, request = request),
        }


class ObrasSerializerCadastro(serializers.ModelSerializer):
    class Meta:
        model = Obras
        fields = ('id','titulo', 'editora', 'foto', 'autores')
