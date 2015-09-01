using System;
using System.Text;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using NLog;
using WPatternMutrack.Data.Repository;
using WPatternMutrack.Data.RepositoryImpl;
using WPatternMutrack.Data.DataContext;
using WPatternMutrack.Model;

namespace WPatternMutrack.Test.Data.Repository
{
    [TestClass]
    public class UserTest
    {
        #region Variable(s)
        private static Logger Log = LogManager.GetCurrentClassLogger();

        public static UserRepository Repository { get; set; }
        #endregion

        #region Methods
        [ClassInitialize]
        public static void ClassInitialize(TestContext testContext)
        {
            Repository = new UserRepositoryImpl(new MutrackDataContext());
        }
        
        [TestMethod]
        public void TestQuery()
        {
            var users = Repository.GetQuery();

            foreach (var user in users)
            {
                Log.Info("TestQuery" + user);
            }
        }
        
        [TestMethod]
        public void TestAll()
        {
            var users = Repository.GetAll();

            foreach (var user in users)
            {
                Log.Info("TestAll:" + user);
            }
        }
        
        [TestMethod]
        public void TestFind()
        {
            var users = Repository.Find(u => u.Name.Contains("user"));

            foreach (var user in users)
            {
                if (user.Name.Contains("user"))
                {
                    Log.Info("TestFind: " + user);
                }
                else
                {
                    Log.Error("TestFind. Invalid user.");
                    Assert.Fail("TestFind. Invalid user {0}.", user);
                }
            }
        }
        
        [TestMethod]
        public void TestSingle()
        {
            var id = Repository.First().Pk;

            var user = Repository.Single(u => u.Pk == id);

            Log.Info("TestSingle: " + user);
        }
        
        [TestMethod]
        public void TestFirst()
        {
            var user = Repository.First();

            Log.Info("TestFirst: " + user);
        }
        
        [TestMethod]
        public void TestFirstOrDefault()
        {
            var user = Repository.FirstOrDefault(u => u.Name.CompareTo("user 1") == 0);

            if (user == null)
            {
                Log.Info("TestFirstOrDefault");
            }
            else
            {
                Log.Error("TestFirstOrDefault selected an user {0}.", user);
                Assert.Fail("TestFirstOrDefault selected an user {0}.", user);
            }
        }
        
        [TestMethod]
        public void TestAdd()
        {
            var newUser = new UserEntity()
            {
                Name = "New User",
                Email = "New Email",
                Password = "New Password"
            };

            Repository.Add(newUser);

            Log.Info("TestAdd: ", newUser);
        }
        
        [TestMethod]
        public void TestDelete()
        {
            var deleteUser = new UserEntity()
            {
                Name = "Delete User",
                Email = "Delete Email",
                Password = "Delete Password"
            };

            Repository.Add(deleteUser);

            Repository.Delete(deleteUser);

            Log.Info("TestDelete: " + deleteUser);
        }
        
        [TestMethod]
        public void TestDeleteByPk()
        {
            var newUser = new UserEntity()
            {
                Name = "Delete By ID",
                Email = "Delete By ID",
                Password = "Delete By ID"
            };

            Repository.Add(newUser);

            var deletedUser = Repository.Delete(newUser.Pk);

            Log.Info("TestDeleteByPk: " + deletedUser);
        }
        
        [TestMethod]
        public void TestUpdate()
        {
            var updateUser = new UserEntity()
            {
                Name = "New User",
                Email = "New User",
                Password = "New User"
            };

            Repository.Add(updateUser);

            updateUser.Name = "Update Name" + DateTime.Now;

            Repository.Update(updateUser);

            Log.Info("TestUpdate: " + updateUser);
        }

        [TestMethod]
        public void TestUpdateOutContext()
        {
            var newUser = new UserEntity()
            {
                Name = "New User",
                Email = "New Email",
                Password = "New Password"
            };

            Repository.Add(newUser);

            var updateUser = new UserEntity()
            {
                Name = "Update User",
                Email = "Update Email",
                Password = "Update Password"
            };

            Repository.Update(updateUser);

            Log.Info("testupdateoutcontext: " + updateUser);
        }
        #endregion
    }
}
