package bj.highfive.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;

import bj.highfive.usermanagement.bean.User;

/**
 * @author tiburce.kouagou
 *
 */
/**
 * @author tiburce.kouagou
 *
 */
public class UserDAO {
	/**
	 * Fonction de connection à la base de données
	 * @return Objet de connection à la BDD
	 */
	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // précise que la BDD de connection est MySQL
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Fonction d'insertion de l'utilisateur dans la BDD
	 * 
	 * @param user - utilisateur à enrégistrer dans la BDD
	 * @return true si l'utilisateur a été enrégistrer, false sinon
	 */
	public static boolean createUser(User user) {
		// 1- Enrégistrer la BDD de connection
		// 2- Récupérer l'objet de conection
		Connection conn = getConnection();
		int res = 0;

		// 3- Créer la requête
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email, country) VALUES(?, ?, ?);");
			// 4- Exécuter la requête
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getCountry());

			res = stmt.executeUpdate();
			// 5- Fermer la connection
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res > 0 ? true : false;
	}

	/**
	 * Fonction de récupération de tous les utilisateurs de la BDD
	 * @return Retourne une liste d'objet contenant tous les utilisateurs de la BDD
	 */
	public static List<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();

		try {
			Connection conn = getConnection(); // récupération de l'objet de connection

			// préparation de la requête
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users;");

			// exécution de la requête
			ResultSet result = stmt.executeQuery(); // import java.sql.ResultSet;

			// stockage des utilisateurs dans la collection userList
			// result.hasRows(); // permet de vérifier s'il y a au moins un résultat

			while (result.next()) {
				User u = new User(); // création du object user

				u.setId(result.getInt("id")); // stockage du user_id de la BDD dans le champs id
				u.setName(result.getString("name")); // stockage du user_name de la BDD dans le champs name
				u.setEmail(result.getString("email")); // stockage du user_email de la BDD dans le champs email
				u.setCountry(result.getString("country")); // stockage du user_country de la BDD dans le champs country

				userList.add(u); // ajout de l'utilisateur dans la liste qui sera retournée (userList)
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	/**
	 * Fonction de récupération d'un utilisateur selon son id
	 * @param id identifiant de l'utilisateur dans la BDD
	 * @return Retour un objet java User correspondant à l'utilisateur correspondant
	 */
	public static User getUserById(int id) {
		User user = new User(); // création d'un obj java pour contenir les informations de l'utilsateur
								// concerné

		Connection conn = getConnection(); // connection à la BDD

		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?;");

			stmt.setInt(1, id); // 1 correspond au ? n°1 dans la requête préparée

			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				user.setId(result.getInt(1)); // result.getInt(1) => récupération de la valeur du champ "id"
				user.setName(result.getString(2));
				user.setEmail(result.getString(3));
				user.setCountry(result.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * Fonction de suppression d'un utilisateur
	 * @param id identifiant de l'utilisateur à supprimer dans la BDD
	 * @return -1 si un problème est survenu,
	 * 1 ou 2 si la suppression a été effectuée
	 */
	public static int deleteUserById(int id) {
		int status = -1;

		Connection conn = getConnection();

		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?;");

			stmt.setInt(1, id); // mapping entre le ? et le paramètre "id"

			status = stmt.executeUpdate(); // retourne 1 ou 2 tout se passe bien
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	/**
	 * Fonction de mise à jour des informations de l'utilisateur
	 * @param u l'objet java représentant l'utilisateur
	 * @return -1 si un problème est survenu,
	 * 1 ou 2 si la mise à jour a été faite
	 */
	public static boolean updateUser(User u) {
		Connection conn = getConnection();
		int res = -1;

		// 3- Créer la requête
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?;");
			// 4- Exécuter la requête
			stmt.setString(1, u.getName());
			stmt.setString(2, u.getEmail());
			stmt.setString(3, u.getCountry());
			stmt.setInt(4, u.getId());

			res = stmt.executeUpdate();
			// 5- Fermer la connection
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res > 0;
	}

}
