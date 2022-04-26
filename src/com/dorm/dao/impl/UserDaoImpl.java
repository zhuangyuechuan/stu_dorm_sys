package com.dorm.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.dorm.bean.DormBuild;
import com.dorm.bean.User;
import com.dorm.dao.UserDao;
import com.dorm.util.ConnectionFactory;
import com.dorm.util.Db;
//import com.mysql.cj.x.protobuf.Mysqlx.ClientMessages.Builder;

public class UserDaoImpl implements UserDao {

	@Override
	public User findByStuCodeAndPass(String stuCode, String password) {
		
		//1.获取连接（数据库地址 用户名 密码）
		//Db db = new Db();
		Connection connection = Db.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			//2.SQL语句
			String sql = "select * from tb_user where stu_code = ? and password=? and disabled = 0";
			
			//3.获取集装箱
			preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, stuCode);
			preparedStatement.setString(2, password);
			
			//4.执行SQL,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				User user = new User();
				//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
				user.setId(rs.getInt("id"));
				user.setCreateUserId(rs.getInt("create_user_id"));
				user.setDisabled(rs.getInt("disabled"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setDormCode(rs.getString("dorm_Code"));
				user.setName(rs.getString("name"));
				user.setPassWord(rs.getString("passWord"));
				user.setRoleId(rs.getInt("role_id"));
				user.setSex(rs.getString("sex"));
				user.setStuCode(rs.getString("stu_code"));
				user.setTel(rs.getString("tel"));
				
				return user;
			}
			
			
			//5.获取执行后的结果
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		
		return null;
	}
	
	@Override
	public String findManagerStuCodeMax() {
		//1.获取连接（数据库地址 用户名 密码）
				//Db db = new Db();
				Connection connection = Db.getConnection();
				PreparedStatement preparedStatement =null;
				ResultSet rs = null;
				
				try {
					//2.SQL语句
					//IFNULL(参数1，参数2)函数，用于判断第一个表达式是否为NULL，如果为NULL，则返回第二个参数的值。如果不为NULL，就返回第一个参数的值
					String sql = "select IFNULL( Max(stu_code),20191023)+1 from tb_user where role_id =1";
					
					//3.获取集装箱
					preparedStatement = connection.prepareStatement(sql);
					
					
					//4.执行SQL,查询的结果封装在ResultSet
					rs = preparedStatement.executeQuery();
					
					rs.next();
					return rs.getString(1);
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					ConnectionFactory.close(connection, preparedStatement, rs);
				}
		return null;
	}
	public Integer saveManager(User user) {
		//1.获取连接（数据库地址 用户名 密码）
		//Db db = new Db();
		Connection connection = Db.getConnection();
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		try {
			//2.SQL语句
			//IFNULL(参数1，参数2)函数，用于判断第一个表达式是否为NULL，如果为NULL，则返回第二个参数的值。如果不为NULL，就返回第一个参数的值
			String sql = "INSERT INTO tb_user(NAME,PASSWORD,stu_code,sex,tel,role_id,create_user_id,disabled) VALUE(?,?,?,?,?,?,?,?)";
			
			//3.获取集装箱
			preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPassWord());
			preparedStatement.setString(3, user.getStuCode());
			preparedStatement.setString(4, user.getSex());
			preparedStatement.setString(5, user.getTel());
			preparedStatement.setInt(6, user.getRoleId());
			preparedStatement.setInt(7, user.getCreateUserId());
			preparedStatement.setInt(8, user.getDisabled());
			
			
			//4.执行SQL,查询的结果封装在ResultSet
			 preparedStatement.executeUpdate();
			 
			 resultSet = preparedStatement.getGeneratedKeys();
			 resultSet.next();
			 return resultSet.getInt(1);
			 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	@Override
	public List<User> findManager(String sql) {
		//1.获取连接（数据库地址 用户名 密码）
				//Db db = new Db();
				Connection connection = Db.getConnection();
				PreparedStatement preparedStatement = null;
				ResultSet rs = null;
				
				try {
					
					//3.获取集装箱
					preparedStatement = connection.prepareStatement(sql);
					
					//4.执行SQL,查询的结果封装在ResultSet
					rs = preparedStatement.executeQuery();
					
					//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
					//如有数据，就进入循环体，封装该行数据 
					List<User> users = new ArrayList<User>();
					while (rs.next()) {
						User user = new User();
						//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
						user.setId(rs.getInt("id"));
						user.setCreateUserId(rs.getInt("create_user_id"));
						user.setDisabled(rs.getInt("disabled"));
						user.setName(rs.getString("name"));
						user.setPassWord(rs.getString("passWord"));
						user.setRoleId(rs.getInt("role_id"));
						user.setSex(rs.getString("sex"));
						user.setStuCode(rs.getString("stu_code"));
						user.setTel(rs.getString("tel"));
						
						users.add(user);
					}
					
					return users;
					//5.获取执行后的结果
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					ConnectionFactory.close(connection, preparedStatement, rs);
				}
		return null;
	}

	@Override
	public User findById(int id) {
		//1.获取连接（数据库地址 用户名 密码）
				//Db db = new Db();
				Connection connection = Db.getConnection();
				PreparedStatement preparedStatement = null;
				ResultSet rs = null;
				
				try {
					//2.SQL语句
					String sql = "select * from tb_user where id = ?";
					
					//3.获取集装箱
					preparedStatement = connection.prepareStatement(sql);
					//索引从1开始
					preparedStatement.setInt(1, id);
					
					//4.执行SQL,查询的结果封装在ResultSet
					rs = preparedStatement.executeQuery();
					
					//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
					//如有数据，就进入循环体，封装该行数据
					while (rs.next()) {
						User user = new User();
						//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
						user.setId(rs.getInt("id"));
						user.setCreateUserId(rs.getInt("create_user_id"));
						user.setDisabled(rs.getInt("disabled"));
						user.setName(rs.getString("name"));
						user.setPassWord(rs.getString("passWord"));
						user.setRoleId(rs.getInt("role_id"));
						user.setSex(rs.getString("sex"));
						user.setStuCode(rs.getString("stu_code"));
						user.setTel(rs.getString("tel"));
						user.setDormBuildId(rs.getInt("dormBuildId"));
						user.setDormCode(rs.getString("dorm_Code"));
						
						return user;
					}
					
					
					//5.获取执行后的结果
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					ConnectionFactory.close(connection, preparedStatement, rs);
				}
		return null;
	}

	@Override
	public void updateManager(User user) {
		//① 获取连接（数据库地址  用户名 密码）
				Connection  connection = 	Db.getConnection();
				PreparedStatement preparedStatement = null;
				ResultSet  resultSet = null;
				try {
					//② 准备SQL语句
					//IFNULL(参数1，参数2)函数，用于判断第一个表达式是否为NULL，如果为NULL，则返回第二个参数的值。如果不为NULL，就返回第一个参数的值
					String sql = "UPDATE tb_user SET NAME= ? ,PASSWORD=?,sex=?,tel=?,disabled= ? WHERE id = ?";
					
					//③ 获取集装箱或者说是车  Statement.RETURN_GENERATED_KEYS指定返回生成的注解
					 preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, user.getName());
					preparedStatement.setString(2, user.getPassWord());
					preparedStatement.setString(3, user.getSex());
					preparedStatement.setString(4, user.getTel());
					preparedStatement.setInt(5, user.getDisabled());
					preparedStatement.setInt(6, user.getId());
					
					//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
					 preparedStatement.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					ConnectionFactory.close(connection, preparedStatement, resultSet);
				}
	}

	@Override
	public void updatePassWord(User userCur) {
		//① 获取连接（数据库地址  用户名 密码）
				Connection  connection = 	Db.getConnection();
				PreparedStatement preparedStatement = null;
				ResultSet  resultSet = null;
				try {
					//② 准备SQL语句
					//IFNULL(参数1，参数2)函数，用于判断第一个表达式是否为NULL，如果为NULL，则返回第二个参数的值。如果不为NULL，就返回第一个参数的值
					String sql = "UPDATE tb_user SET PASSWORD=? WHERE id = ?";
					
					//③ 获取集装箱或者说是车  Statement.RETURN_GENERATED_KEYS指定返回生成的注解
					 preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, userCur.getPassWord());
					preparedStatement.setInt(2, userCur.getId());
					
					//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
					 preparedStatement.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					ConnectionFactory.close(connection, preparedStatement, resultSet);
				}
		
	}

	
	@Override
	public User findByStuCode(String stuCode) {
		//1.获取连接（数据库地址 用户名 密码）
		//
		Connection connection = Db.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			//2.SQL语句
			String sql = "select * from tb_user where stu_code = ? ";
			
			//3.获取集装箱
			preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, stuCode);
			
			//4.执行SQL,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				User user = new User();
				//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
				user.setId(rs.getInt("id"));
				user.setCreateUserId(rs.getInt("create_user_id"));
				user.setDisabled(rs.getInt("disabled"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setDormCode(rs.getString("dorm_Code"));
				user.setName(rs.getString("name"));
				user.setPassWord(rs.getString("passWord"));
				user.setRoleId(rs.getInt("role_id"));
				user.setSex(rs.getString("sex"));
				user.setStuCode(rs.getString("stu_code"));
				user.setTel(rs.getString("tel"));
				
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
				
		return null;
	}

	
	@Override
	public void saveStudent(User user) {
		//1.获取连接（数据库地址 用户名 密码）
		//
		Connection connection = Db.getConnection();
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		try {
			//2.SQL语句
			//IFNULL(参数1，参数2)函数，用于判断第一个表达式是否为NULL，如果为NULL，则返回第二个参数的值。如果不为NULL，就返回第一个参数的值
			String sql = "INSERT INTO tb_user(NAME,PASSWORD,stu_code,dorm_code,sex,tel,dormBuildId,role_id,create_user_id) VALUE(?,?,?,?,?,?,?,?,?)";
			
			//3.获取集装箱    Statement.RETURN_GENERATED_KEYS指定返回生成的注解
			preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPassWord());
			preparedStatement.setString(3, user.getStuCode());
			preparedStatement.setString(4, user.getDormCode());
			preparedStatement.setString(5, user.getSex());
			preparedStatement.setString(6, user.getTel());
			preparedStatement.setInt(7, user.getDormBuildId());
			preparedStatement.setInt(8, user.getRoleId());
			preparedStatement.setInt(9, user.getCreateUserId());
			
			//4.执行SQL,查询的结果封装在ResultSet
			 preparedStatement.executeUpdate();
			 
				/*
				 * resultSet = preparedStatement.getGeneratedKeys(); 
				 * resultSet.next(); 
				 * Integer id = resultSet.getInt(1);
				 */
				 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, resultSet);
		}
		
	}

	
	@Override
	public List<User> findStudent(String sql) {
		//1.获取连接（数据库地址 用户名 密码）
		//
		Connection connection = Db.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			
			//3.获取集装箱
			preparedStatement = connection.prepareStatement(sql);
			
			//4.执行SQL,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据 
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
				user.setId(rs.getInt("id"));
				user.setCreateUserId(rs.getInt("create_user_id"));
				user.setDisabled(rs.getInt("disabled"));
				user.setName(rs.getString("name"));
				user.setPassWord(rs.getString("passWord"));
				user.setRoleId(rs.getInt("role_id"));
				user.setSex(rs.getString("sex"));
				user.setStuCode(rs.getString("stu_code"));
				user.setTel(rs.getString("tel"));
				user.setDormCode(rs.getString("dorm_code"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				
				DormBuild build = new DormBuild();
				build.setId(rs.getInt("dormBuildId"));
				build.setName(rs.getString("buildName"));
				build.setRemark(rs.getString("remark"));
				user.setDormBuild(build);
				
				users.add(user);
			}
			
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return null;
	}

	
	@Override
	public Integer findTotalNum(String sql) {
		//1.获取连接（数据库地址 用户名 密码）
		//
		Connection connection = Db.getConnection();
		PreparedStatement preparedStatement =null;
		ResultSet rs = null;
		
		try {
			//2.SQL语句
			//3.获取集装箱
			preparedStatement = connection.prepareStatement(sql);
			
			//4.执行SQL,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			rs.next();
			return rs.getInt(1);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return null;
	}

	
	@Override
	public void updateStudent(User user) {
		//① 获取连接（数据库地址  用户名 密码）
		Connection  connection = Db.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  resultSet = null;
		try {
			//② 准备SQL语句
			//IFNULL(参数1，参数2)函数，用于判断第一个表达式是否为NULL，如果为NULL，则返回第二个参数的值。如果不为NULL，就返回第一个参数的值
			String sql = "UPDATE tb_user SET NAME=?,PASSWORD=?,sex=?,tel=?,disabled=?,stu_code=?,dorm_Code=?,dormBuildId=? WHERE id = ?";
			
			//③ 获取集装箱或者说是车  Statement.RETURN_GENERATED_KEYS指定返回生成的注解
			 preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPassWord());
			preparedStatement.setString(3, user.getSex());
			preparedStatement.setString(4, user.getTel());
			preparedStatement.setInt(5, user.getDisabled());
			preparedStatement.setString(6, user.getStuCode());
			preparedStatement.setString(7, user.getDormCode());
			preparedStatement.setInt(8, user.getDormBuildId());
			preparedStatement.setInt(9, user.getId());
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			 preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, resultSet);
		}
		
	}

	
	@Override
	public User findByUserIdAndManager(String sql) {
		//1.获取连接（数据库地址 用户名 密码）
		//
		Connection connection = Db.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			//3.获取集装箱
			preparedStatement = connection.prepareStatement(sql);
		
			//4.执行SQL,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				User user = new User();
				//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
				user.setId(rs.getInt("id"));
				user.setCreateUserId(rs.getInt("create_user_id"));
				user.setDisabled(rs.getInt("disabled"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setDormCode(rs.getString("dorm_Code"));
				user.setName(rs.getString("name"));
				user.setPassWord(rs.getString("passWord"));
				user.setRoleId(rs.getInt("role_id"));
				user.setSex(rs.getString("sex"));
				user.setStuCode(rs.getString("stu_code"));
				user.setTel(rs.getString("tel"));
				
				return user;
			}
			
			
			//5.获取执行后的结果
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return null;
	}

	
	
}
