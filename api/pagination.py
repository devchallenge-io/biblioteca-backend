from rest_framework.pagination import PageNumberPagination
from rest_framework.response import Response

class PaginationCustomizada(PageNumberPagination):
    page_size = 2
    page_size_query_param = 'page_size'
    max_page_size = 6

    def get_paginated_response(self, data):
        return Response(
            {
                'Links': {
                    'next': self.get_next_link(),
                    'previous': self.get_previous_link()
                },
                'results': data
            }
        )