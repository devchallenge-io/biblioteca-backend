using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Library_System.Models
{
    public class Book
    {
        public int BookID           { get; set; }

        [Required]
        public string Title         { get; set; }
        [Required]
        [Display(Name="Serial Number")]
        public string SerialNumber  { get;set; }
        [Display(Name = "Author")]
        public int AuthorID         {get; set; }
        [Display(Name = "Publisher")]
        public int PublisherID      { get; set; }

    }
}
