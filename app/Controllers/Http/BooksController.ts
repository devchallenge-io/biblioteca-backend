import { HttpContextContract } from '@ioc:Adonis/Core/HttpContext'
import { BookDTO } from 'App/Dto/BookDTO'
import BooksRepository from 'App/Repositories/BooksRepository'

export default class BooksController {
  private readonly booksRepository: BooksRepository

  constructor() {
    this.booksRepository = new BooksRepository()
  }

  public async index({}: HttpContextContract) {}

  public async create({ request, response }: HttpContextContract) {
    const book = request.body() as BookDTO
    const books = this.booksRepository.add(book)
    response.status(201).json(books)
  }

  public async update({}: HttpContextContract) {}

  public async destroy({}: HttpContextContract) {}
}
