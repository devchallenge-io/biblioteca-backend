/* eslint-disable @typescript-eslint/explicit-member-accessibility */
import { BookDTO } from 'App/Dto/BookDTO'
import { Book } from 'App/Models/Book'

export default class BooksRepository {
  books: Book[] = []
  id = 0

  public add({ authors, image, publisher, title }: BookDTO): Book[] {
    this.id += 1
    const book: Book = {
      id: this.id,
      titulo: title,
      editora: publisher,
      foto: image,
      autores: authors,
    }
    this.books.push(book)
    return this.books
  }
}
