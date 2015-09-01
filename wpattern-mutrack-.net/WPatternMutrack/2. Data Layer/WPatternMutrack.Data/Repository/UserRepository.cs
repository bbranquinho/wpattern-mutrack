using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WPatternMutrack.Model;

namespace WPatternMutrack.Data.Repository
{
    public interface UserRepository : GenericRepository<UserEntity, Int32>
    {
    }
}
