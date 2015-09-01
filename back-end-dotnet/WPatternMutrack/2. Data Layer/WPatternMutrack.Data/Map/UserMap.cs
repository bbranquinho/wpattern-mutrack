using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WPatternMutrack.Model;

namespace WPatternMutrack.Data.Map
{
    public class UserMap : EntityTypeConfiguration<UserEntity>
    {
        #region Constructor(s)
        public UserMap()
        {
            ToTable("tb_user");

            HasKey(u => u.Pk);

            Property(u => u.Name);
            Property(u => u.Email);
            Property(u => u.Password);
        }
        #endregion 
    }
}
