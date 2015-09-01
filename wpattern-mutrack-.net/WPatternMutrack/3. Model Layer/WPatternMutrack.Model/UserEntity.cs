using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WPatternMutrack.Model.Utils;

namespace WPatternMutrack.Model
{
    public class UserEntity : BaseEntity<Int32>
    {
        #region Properties
        public String Name { get; set; }
        public String Email { get; set; }
        public String Password { get; set; }
        #endregion

        #region Construtor(s)
        public UserEntity() { }
        #endregion
    }
}
