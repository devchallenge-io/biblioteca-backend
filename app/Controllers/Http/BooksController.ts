import { HttpContextContract } from '@ioc:Adonis/Core/HttpContext'

export default class BooksController {
  public async index({}: HttpContextContract) {}

  public async create({ request, response }: HttpContextContract) {
    const book = request.body() as BookDTO
    const books = this.addBook(book)
    response.status(200).json(books)
  }

  public async update({}: HttpContextContract) {}

  public async destroy({}: HttpContextContract) {}
}
