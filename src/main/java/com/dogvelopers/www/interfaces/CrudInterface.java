package com.dogvelopers.www.interfaces;

import com.dogvelopers.www.model.network.Header;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudInterface<Req,Res> {

    Header<Res> create(Req request);

    Header<Res> read(Long id);

    Header<Res> update(Req request);

    Header delete(Long id);
}
