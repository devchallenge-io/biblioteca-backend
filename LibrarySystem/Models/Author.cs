using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Library_System.Models
{
    public class Author
    {
        public int AuthorID { get; set; }
        [Required]
        public string Name { get; set; }
        public ICollection<Book> Books { get; set; }
        
    }
}
