package cz.uhk.fim.RSSFeedReader.gui;

import javax.swing.*;
import java.awt.*;

/**
 *  FlowLayout subclass that fully supports wrapping of components.
 */
public class WrapLayout extends FlowLayout {

	/**
	 * Constructs a new <code>WrapLayout</code> with a left
	 * alignment and a default 5-unit horizontal and vertical gap.
	 */
	WrapLayout() {
		super();
	}

	/**
	 * Returns the preferred dimensions for this layout given the
	 * <i>visible</i> components in the specified target container.
	 * @param target the component which needs to be laid out
	 * @return the preferred dimensions to lay out the
	 * subcomponents of the specified container
	 */
	@Override
	public Dimension preferredLayoutSize(Container target)
		{
			return layoutSize(target, true);
		}

	/**
	 * Returns the minimum dimensions needed to layout the <i>visible</i>
	 * components contained in the specified target container.
	 * @param target the component which needs to be laid out
	 * @return the minimum dimensions to lay out the
	 * subcomponents of the specified container
	 */
	@Override
	public Dimension minimumLayoutSize(Container target)
		{
			Dimension minimum = layoutSize(target, false);
			minimum.width -= (getHgap() + 1);
			return minimum;
		}

	/**
	 * Returns the minimum or preferred dimension needed to layout the target
	 * container.
	 *
	 * @param target target to get layout size for
	 * @param preferred should preferred size be calculated
	 * @return the dimension to layout the target container
	 */
	private Dimension layoutSize(Container target, boolean preferred)
		{
			synchronized (target.getTreeLock()) {

				int targetWidth;
				Container container = target;

				while (container.getSize().width == 0 && container.getParent() != null) {
					container = container.getParent();
				}

				targetWidth = container.getSize().width;

				if (targetWidth == 0)
					targetWidth = Integer.MAX_VALUE;

				int hgap = getHgap();
				int vgap = getVgap();
				Insets insets = target.getInsets();
				int horizontalInsetsAndGap = insets.left + insets.right + (hgap * 2);
				int maxWidth = targetWidth - horizontalInsetsAndGap;

				Dimension dim = new Dimension(0, 0);
				int rowWidth = 0;
				int rowHeight = 0;
				int nmembers = target.getComponentCount();

				for (int i = 0; i < nmembers; i++) {
					Component m = target.getComponent(i);

					if (m.isVisible()) {
						Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();

						if (rowWidth + d.width > maxWidth) {
							addRow(dim, rowWidth, rowHeight);
							rowWidth = 0;
							rowHeight = 0;
						}

						if (rowWidth != 0) {
							rowWidth += hgap;
						}
						rowWidth += d.width;
						rowHeight = Math.max(rowHeight, d.height);
					}
				}

				addRow(dim, rowWidth, rowHeight);

				dim.width += horizontalInsetsAndGap;
				dim.height += insets.top + insets.bottom + vgap * 2;

				Container scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane.class, target);

				if (scrollPane != null && target.isValid()) {
					dim.width -= (hgap + 1);
				}
				return dim;
			}
		}

	private void addRow(Dimension dim, int rowWidth, int rowHeight) {
		dim.width = Math.max(dim.width, rowWidth);

		if (dim.height > 0) {
			dim.height += getVgap();
		}
		dim.height += rowHeight;
	}
}