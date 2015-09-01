using System;
using System.Text;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using WPatternMutrack.Data.DataContext;
using NLog;
using WPatternMutrack.Model;

namespace WPatternMutrack.Test.Data.DataContext
{
    [TestClass]
    public class DataContextTest
    {
        #region Variable(s)
        private static Logger Log = LogManager.GetCurrentClassLogger();
        #endregion

        #region Properties
        private static MutrackDataContext DataContext { get; set; }
        #endregion

        #region Test(s)
        [ClassInitialize]
        public static void ClassInitialize(TestContext testContext)
        {
            DataContext = new MutrackDataContext();
        }

        [TestMethod]
        public void ListUsersTest()
        {
            var users = DataContext.Set<UserEntity>();

            foreach (var user in users) {
                Log.Info(user);
            }
        }
        #endregion
    }
}
