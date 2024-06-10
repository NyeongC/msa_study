package org.msa.item.cotroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msa.item.domain.Item;
import org.msa.item.dto.ItemDTO;
import org.msa.item.dto.ResponseDTO;
import org.msa.item.dto.constant.ItemType;
import org.msa.item.exception.ApiException;
import org.msa.item.service.ItemService;
import org.msa.item.valid.ItemTypeValid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "v1/item")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ItemController {

	private final ItemService itemService;
	
	@RequestMapping(value="/add/{itemType}", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> add(@Valid @RequestBody ItemDTO itemDTO, @ItemTypeValid @PathVariable String itemType) throws Exception{
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();

		itemDTO.setItemType(itemType);
		itemService.insertItem(itemDTO);
		log.debug("requset add item id = {}", itemDTO.getId());

		try {
			Integer.parseInt("test");
		}catch (Exception e) {
			throw new ApiException("test에러");
		}
		
		responseBuilder.code("200").message("success");
		return ResponseEntity.ok(responseBuilder.build());
	}

}
