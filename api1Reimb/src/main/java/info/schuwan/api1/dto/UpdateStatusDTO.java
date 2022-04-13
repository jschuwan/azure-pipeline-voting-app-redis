package info.schuwan.api1.dto;


public class UpdateStatusDTO {
    private String newStatus;

    public UpdateStatusDTO(String newStatus) {
        this.newStatus = newStatus;
    }

    public UpdateStatusDTO() {
        setNewStatus("APPROVED");
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    @Override
    public String toString() {
        return "UpdateStatusDTO{" +
                "newStatus='" + newStatus + '\'' +
                '}';
    }
}
