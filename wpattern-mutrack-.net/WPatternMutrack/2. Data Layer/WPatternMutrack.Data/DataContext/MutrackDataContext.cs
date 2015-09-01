using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WPatternMutrack.Data.Map;
using WPatternMutrack.Model;

namespace WPatternMutrack.Data.DataContext
{
    public class MutrackDataContext : DbContext
    {
        #region Properties
        #endregion

        #region Constructor(s)
        public MutrackDataContext() : base("MutrackConnectionString")
        {
            Database.SetInitializer<MutrackDataContext>(new MutrackDataContextInitializer());

            Configuration.LazyLoadingEnabled = false;
            Configuration.ProxyCreationEnabled = false;
        }
        #endregion

        #region Method(s)
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new UserMap());

            base.OnModelCreating(modelBuilder);
        }
        #endregion

        #region Nested Class
        private class MutrackDataContextInitializer : DropCreateDatabaseIfModelChanges<MutrackDataContext>
        {
            #region Method(s)
            protected override void Seed(MutrackDataContext context)
            {
                var users = context.Set<UserEntity>();

                UserEntity user1 = new UserEntity() { Email = "user1@email.com", Name = "User 1", Password = "Password 1" };
                UserEntity user2 = new UserEntity() { Email = "user2@email.com", Name = "User 2", Password = "Password 2" };
                UserEntity user3 = new UserEntity() { Email = "user3@email.com", Name = "User 3", Password = "Password 3" };

                users.Add(user1);
                users.Add(user2);
                users.Add(user3);
                context.SaveChanges();

                base.Seed(context);
            }
            #endregion
        }
        #endregion
    }
}
