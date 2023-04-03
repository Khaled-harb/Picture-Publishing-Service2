package Package.Services;

import java.io.IOException;
import java.util.List;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

import Package.Entity.FileData;
import Package.Entity.Users;
import Package.Security.CustomerUserDetails;
import Package.UserReposetory.FileDataRepository;
import Package.UserReposetory.UserReposetory;

@Service
public class Services  implements UserDetailsService{
    @Autowired
	UserReposetory repo;
    @Autowired
	FileDataRepository fileDataRepository;
	public Users addUser(Users user) {

		return repo.save(user);
	}
	public Users finfById (int id ) {
		  
		  return repo.findById(id).get();
	  }
	public Users findByEmail(String email ) {
		  return repo.findByEmail(email);
	  }
	public List<Users> AllUsers() {
		return (List<Users>) repo.findAll();
	}
    private final String FOLDER_PATH="/Users/javatechie/Desktop/MyFIles/";

	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
	        String filePath=FOLDER_PATH+file.getOriginalFilename();

	        FileData fileData=fileDataRepository.save(FileData.builder()
	                .name(file.getOriginalFilename())
	                .type(file.getContentType())
	                .filePath(filePath).build());

	        file.transferTo(new File(filePath));

	        if (fileData != null) {
	            return "file uploaded successfully : " + filePath;
	        }
	        return null;
	    }

	    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
	        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
	        String filePath=fileData.get().getFilePath();
	        byte[] images = Files.readAllBytes(new File(filePath).toPath());
	        return images;
	    }
	    @Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		  Users appUser =	repo.findByEmail(username);
		  
		  if (appUser == null) {
			  
			  throw new UsernameNotFoundException("This User Not found with selected user name :- " + username);
		  }
			
			return new CustomerUserDetails(appUser);
		}
}
