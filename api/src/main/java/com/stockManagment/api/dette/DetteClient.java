package com.stockManagment.api.dette;

import com.stockManagment.api.versement.VersementAutre;
import com.stockManagment.api.versement.VersementClient;
import jakarta.persistence.OneToMany;

import java.util.List;

public class DetteClient extends Dette{

    @OneToMany(mappedBy = "dette")
    private List<VersementClient> versementClientList;
}
