import { HttpContextContract } from '@ioc:Adonis/Core/HttpContext'

type Book = {
  id: number
  titulo: string
  editora: string
  foto: string
  autores: string[]
}

type BookDTO = {
  title: string
  publisher: string
  image: string
  authors: string[]
}

let id = 0
let books: Book[] = []

export default class BooksController {
  public async index({}: HttpContextContract) {}

  public async create({ request, response }: HttpContextContract) {
    const book = request.body() as BookDTO
    const books = this.addBook(book)
    response.status(200).json(books)
  }

  public async update({}: HttpContextContract) {}

  public async destroy({}: HttpContextContract) {}

  private addBook({ authors, image, publisher, title }: BookDTO): Book[] {
    id += 1
    const book: Book = {
      id,
      titulo: title,
      editora: publisher,
      foto: image,
      autores: authors,
    }
    books.push(book)
    return books
  }
}
