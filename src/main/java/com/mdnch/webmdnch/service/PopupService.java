package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.PopupRequest;
import com.mdnch.webmdnch.dto.response.PopupResponse;

import java.util.List;

public interface PopupService {
    PopupResponse createPopup(PopupRequest popupRequest);
    List<PopupResponse> getAllPopups();
    PopupResponse findByIdPopup(Integer id);
    PopupResponse updatePopup(Integer id, PopupRequest request);
    void deletePopup(Integer id);
}
