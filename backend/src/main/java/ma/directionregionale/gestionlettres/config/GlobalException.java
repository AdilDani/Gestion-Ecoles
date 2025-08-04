package ma.directionregionale.gestionlettres.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalException extends RuntimeException {

    private String code;
    private final String message;

    public GlobalException(String code, String message){
        this.code=code;
        this.message=message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

}
