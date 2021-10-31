package sdkdata.entity;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "project_table")
@RequiredArgsConstructor
@Getter
@Setter
public class Project {

    // key of IMQA Project
    @Column(name="project_id")
    private String projectId;

    // checks if rendering works
    @Column(name="render_yn")
    private Boolean renderYn;

    // checks if agent is imported
    @Column(name="agent_yn")
    private Boolean agentYn;

    // gets for the os information
    @Column(name="os_info")
    private String osInfo;

    // checks if request - response is working
    @Column(name="request_yn")
    private Boolean requestYn;

    // checks if it is webview is working
    @Column(name="webview_yn")
    private Boolean webviewYn;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
}

