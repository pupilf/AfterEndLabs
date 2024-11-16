package POJO;

public class Brand {
    private String name;
    private String profile;
    public Brand(){}
    public Brand(String name,String profile){
        this.name=name;
        this.profile=profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }


    public void setProfile(String profile) {
        this.profile = profile;
    }
    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }

}
