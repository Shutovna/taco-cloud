package tacos.data;

import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements UserRepository{
    @Override
    public void saveUser() {
        System.out.println("saveUser");
    }
}
