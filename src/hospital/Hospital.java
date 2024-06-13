package hospital;

public class Hospital {
//    Patient Id (PK) Patient name(NN) Disease (NN) Doctor name (NN) Fees (NN)
    private int p_id;
    private int p_age;
    private String p_name;

    @Override
    public String toString() {
        return "Hospital{" +
                "p_id=" + p_id +
                ", p_age=" + p_age +
                ", p_name='" + p_name + '\'' +
                ", p_disease='" + p_disease + '\'' +
                ", d_name='" + d_name + '\'' +
                ", m_Id=" + m_Id +
                ", m_Name='" + m_Name + '\'' +
                ", m_dosage=" + m_dosage +
                '}';
    }

    private  String p_disease;

    public Hospital(int p_id, int p_age, String p_name, String p_disease, String d_name, int m_Id, String m_Name, int m_dosage) {
        this.p_id = p_id;
        this.p_age = p_age;
        this.p_name = p_name;
        this.p_disease = p_disease;
        this.d_name = d_name;
        this.m_Id = m_Id;
        this.m_Name = m_Name;
        this.m_dosage = m_dosage;
    }

    private  String d_name;

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getP_age() {
        return p_age;
    }

    public void setP_age(int p_age) {
        this.p_age = p_age;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_disease() {
        return p_disease;
    }

    public void setP_disease(String p_disease) {
        this.p_disease = p_disease;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public int getM_Id() {
        return m_Id;
    }

    public void setM_Id(int m_Id) {
        this.m_Id = m_Id;
    }

    public String getM_Name() {
        return m_Name;
    }

    public void setM_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    public int getM_dosage() {
        return m_dosage;
    }

    public void setM_dosage(int m_dosage) {
        this.m_dosage = m_dosage;
    }

    private int m_Id;
    private String m_Name;
    private int m_dosage;


}
