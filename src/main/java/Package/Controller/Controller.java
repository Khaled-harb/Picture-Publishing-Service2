package Package.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Package.Entity.Users;
import Package.RoleServices.RoleServices;
import Package.Services.Services;

@RestController
public class Controller {

	@Autowired
	Services userService;
	@Autowired
	RoleServices RoleService;

	@GetMapping("/LandingPage")

	public String ViewHomePage() {

		return "Home Page";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")

	@GetMapping("/adminPage")
	public String AdminPage() {

		return "/Admin Page";
	}

	@PostMapping("/registration")
	public Users addUser(@RequestBody Users User) {

		return userService.addUser(User);
	}

	@GetMapping("/public/Landing-page")
	public String LandingPage() {
		return "all accepted pictures' URLs";
	}

	@GetMapping("/Admin")
	public String AdmminPage() {
		return "Admin Page";
	}
	@GetMapping("/UserPage")
	public String UserPage() {
		return "Admin Page";
	}

	@GetMapping("/Admin/Uploaded-Pics")
	public String UploadedPics() {
		return "Uploaded-Pics Page";
	}

	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return userService.AllUsers();
	}

	// ****************************************
	// File Controller
	@PreAuthorize("hasRole('ROLE_USER')")

	@PostMapping("/users/fileSystem")
	public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
		String uploadImage = userService.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}
	@PreAuthorize("hasRole('ROLE_USER')")


	@GetMapping("/users/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData = userService.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);

	}
}
