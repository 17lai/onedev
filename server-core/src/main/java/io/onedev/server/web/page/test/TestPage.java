package io.onedev.server.web.page.test;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import io.onedev.server.OneDev;
import io.onedev.server.entitymanager.ProjectManager;
import io.onedev.server.model.Project;
import io.onedev.server.web.asset.mermaid.MermaidResourceReference;
import io.onedev.server.web.page.base.BasePage;
import io.onedev.server.web.resource.ArchiveResource;
import io.onedev.server.web.resource.ArchiveResourceReference;

@SuppressWarnings("serial")
public class TestPage extends BasePage {

	public TestPage(PageParameters params) {
		super(params);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new AjaxLink<Void>("test") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				Project project = OneDev.getInstance(ProjectManager.class).load(160L);
				System.out.println(RequestCycle.get().urlFor(new ArchiveResourceReference(), ArchiveResource.paramsOf(project.getId(), "master", ArchiveResource.FORMAT_ZIP)));
			}

		});
		
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(new TestResourceReference()));
		response.render(JavaScriptHeaderItem.forReference(new MermaidResourceReference()));
		response.render(OnDomReadyHeaderItem.forScript("onedev.server.test.onDomReady();"));
	}		

}
