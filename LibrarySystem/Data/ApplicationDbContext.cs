using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using Library_System.Models;

namespace LibrarySystem.Data
{
    public class ApplicationDbContext : IdentityDbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
        public DbSet<Library_System.Models.Author> Author { get; set; }
        public DbSet<Library_System.Models.Book> Book { get; set; }
        public DbSet<Library_System.Models.Publisher> Publisher { get; set; }
    }
}
