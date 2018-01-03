/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.mycompany.myapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.Character;
import com.mycompany.myapp.domain.Script;
import com.mycompany.myapp.domain.Skill;
import com.mycompany.myapp.domain.Skill_;
import com.mycompany.myapp.dto.support.PageRequestByExample;
import com.mycompany.myapp.dto.support.PageResponse;
import com.mycompany.myapp.repository.CharacterRepository;
import com.mycompany.myapp.repository.ScriptRepository;
import com.mycompany.myapp.repository.SkillRepository;

/**
 * A simple DTO Facility for Skill.
 */
@Service
public class SkillDTOService {

    @Inject
    private SkillRepository skillRepository;
    @Inject
    private ScriptDTOService scriptDTOService;
    @Inject
    private ScriptRepository scriptRepository;
    @Inject
    private CharacterDTOService characterDTOService;
    @Inject
    private CharacterRepository characterRepository;

    @Transactional(readOnly = true)
    public SkillDTO findOne(Integer id) {
        return toDTO(skillRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<SkillDTO> complete(String query, int maxResults) {
        List<Skill> results = skillRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<SkillDTO> findAll(PageRequestByExample<SkillDTO> req) {
        Example<Skill> example = null;
        Skill skill = toEntity(req.example);

        if (skill != null) {
            ExampleMatcher matcher = ExampleMatcher.matching() //
                    .withMatcher(Skill_.name.getName(), match -> match.ignoreCase().startsWith());

            example = Example.of(skill, matcher);
        }

        Page<Skill> page;
        if (example != null) {
            page = skillRepository.findAll(example, req.toPageable());
        } else {
            page = skillRepository.findAll(req.toPageable());
        }

        List<SkillDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public SkillDTO save(SkillDTO dto) {
        if (dto == null) {
            return null;
        }

        final Skill skill;

        if (dto.isIdSet()) {
            Skill skillTmp = skillRepository.findOne(dto.id);
            if (skillTmp != null) {
                skill = skillTmp;
            } else {
                skill = new Skill();
                skill.setId(dto.id);
            }
        } else {
            skill = new Skill();
        }

        skill.setName(dto.name);

        skill.setPosition(dto.position);

        skill.setOnInit(dto.onInit);

        skill.setCreationDate(dto.creationDate);

        skill.setCreationAuthor(dto.creationAuthor);

        skill.setLastModificationDate(dto.lastModificationDate);

        skill.setLastModificationAuthor(dto.lastModificationAuthor);

        skill.setVersion(dto.version);

        if (dto.skill2 == null) {
            skill.setSkill2(null);
        } else {
            Skill skill2 = skill.getSkill2();
            if (skill2 == null || (skill2.getId().compareTo(dto.skill2.id) != 0)) {
                skill.setSkill2(skillRepository.findOne(dto.skill2.id));
            }
        }

        if (dto.script == null) {
            skill.setScript(null);
        } else {
            Script script = skill.getScript();
            if (script == null || (script.getId().compareTo(dto.script.id) != 0)) {
                skill.setScript(scriptRepository.findOne(dto.script.id));
            }
        }

        skill.getTheChars().clear();
        if (dto.theChars != null) {
            dto.theChars.stream().forEach(anChar -> skill.addAnChar(characterRepository.findOne(anChar.id)));
        }

        return toDTO(skillRepository.save(skill));
    }

    /**
     * Converts the passed skill to a DTO.
     */
    public SkillDTO toDTO(Skill skill) {
        return toDTO(skill, 1);
    }

    /**
     * Converts the passed skill to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param skill
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public SkillDTO toDTO(Skill skill, int depth) {
        if (skill == null) {
            return null;
        }

        SkillDTO dto = new SkillDTO();

        dto.id = skill.getId();
        dto.name = skill.getName();
        dto.position = skill.getPosition();
        dto.onInit = skill.getOnInit();
        dto.creationDate = skill.getCreationDate();
        dto.creationAuthor = skill.getCreationAuthor();
        dto.lastModificationDate = skill.getLastModificationDate();
        dto.lastModificationAuthor = skill.getLastModificationAuthor();
        dto.version = skill.getVersion();
        if (depth-- > 0) {
            dto.skill2 = toDTO(skill.getSkill2(), depth);
            dto.script = scriptDTOService.toDTO(skill.getScript(), depth);
            final int fdepth = depth;
            dto.theChars = skill.getTheChars().stream().map(anChar -> characterDTOService.toDTO(anChar, fdepth)).collect(Collectors.toList());
        }

        return dto;
    }

    /**
     * Converts the passed dto to a Skill.
     * Convenient for query by example.
     */
    public Skill toEntity(SkillDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a Skill.
     * Convenient for query by example.
     */
    public Skill toEntity(SkillDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        Skill skill = new Skill();

        skill.setId(dto.id);
        skill.setName(dto.name);
        skill.setPosition(dto.position);
        skill.setOnInit(dto.onInit);
        skill.setCreationDate(dto.creationDate);
        skill.setCreationAuthor(dto.creationAuthor);
        skill.setLastModificationDate(dto.lastModificationDate);
        skill.setLastModificationAuthor(dto.lastModificationAuthor);
        skill.setVersion(dto.version);
        if (depth-- > 0) {
            skill.setSkill2(toEntity(dto.skill2, depth));
            skill.setScript(scriptDTOService.toEntity(dto.script, depth));
        }

        return skill;
    }
}