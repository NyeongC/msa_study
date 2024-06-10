package org.msa.item.cotroller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@OpenAPIDefinition(info = @Info(title = "물품 처리요청 API", description = "물품 처리요청 API", version = "v1"))
@RestController
@RequestMapping(value = "v1/item")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ItemController {

	private final ItemService itemService;

	@Operation(summary = "물품등록 요청", description = "물품 등록을 진행할 수 있다.", tags = { "addItem" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "SUCCESS"),
			@ApiResponse(responseCode = "501", description = "API EXCEPTION")
	})
	@RequestMapping(value="/add/{itemType}", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> add(@Valid @RequestBody ItemDTO itemDTO, @ItemTypeValid @PathVariable String itemType) throws Exception{
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();

		itemDTO.setItemType(itemType);
		itemService.insertItem(itemDTO);
		log.debug("requset add item id = {}", itemDTO.getId());
		
		responseBuilder.code("200").message("success");
		return ResponseEntity.ok(responseBuilder.build());
	}

}
