function ShowHisto(h,fig,varargin)
titre='histogramme';
dom=0:255;
style='bar';
nVarargs = length(varargin);
for k = 1:2:nVarargs
    if strcmp(varargin{k},'dom'),dom=varargin{k+1};end
    if strcmp(varargin{k},'title'),titre=varargin{k+1};end
end
figure(fig)
clf
if size(h,2)==1
    plot(dom,h);
else
    col=['r','g','b'];
    for k=1:3
        plot(dom,h(:,k),col(k));
        hold on
    end
    grid on
    legend('red','green','blue');
end
grid on
title(titre);
axis([min(dom) max(dom) min(h(:)) max(h(:))]);