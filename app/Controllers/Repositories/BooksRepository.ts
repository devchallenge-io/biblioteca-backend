/* eslint-disable @typescript-eslint/explicit-member-accessibility */
import { Book } from 'App/Models/Book'

type BookDTO = {
  title: string
  publisher: string
  image: string
  authors: string[]
}

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
