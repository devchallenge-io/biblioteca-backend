import { BookDTO } from 'App/Dto/BookDTO'
import { Book } from 'App/Models/Book'

let books: Book[] = []
let id = 0
export default class BooksRepository {
  public add({ authors, image, publisher, title }: BookDTO): Book[] {
    id += 1
    const book: Book = {
      id: id,
      titulo: title,
      editora: publisher,
      foto: image,
      autores: authors,
    }
    books.push(book)
    return books
  }
}
