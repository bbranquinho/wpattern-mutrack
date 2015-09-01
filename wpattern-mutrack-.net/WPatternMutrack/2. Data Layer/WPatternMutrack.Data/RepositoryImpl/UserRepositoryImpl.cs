using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WPatternMutrack.Data.Repository;
using WPatternMutrack.Model;

namespace WPatternMutrack.Data.RepositoryImpl
{
    public class UserRepositoryImpl : GenericRepositoryImpl<UserEntity, Int32>, UserRepository
    {
        #region Constructor(s)
        public UserRepositoryImpl(DbContext dataContext) : base(dataContext)
        {
        }
        #endregion
    }
}
